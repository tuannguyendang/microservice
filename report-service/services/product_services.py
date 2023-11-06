from entity import Product


class ProductService(object):
    def product_report(self):
        results = []
        products = Product.query.all()
        for p in products:
            results.append({'name': p.name, 'amount': self.format_amount('{:.2f}%', p.amount)})
        return results

    def format_amount(self, formatType, amount):
        return formatType.format(amount)
